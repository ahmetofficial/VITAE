/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('ALBUMS_HAVE_VOTES', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    album_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'ALBUMS',
        key: 'album_id'
      }
    }
  }, {
    tableName: 'ALBUMS_HAVE_VOTES'
  });
};
